package api.resources;

import beans.crud.PlaylistBean;
import beans.external.SongServiceBean;
import entities.Playlist;
import entities.PlaylistSong;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import pojo.ResponseError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("playlists")
@ApplicationScoped
@Tags(value = @Tag(name = "playlists"))
public class PlaylistResource {

    @Inject
    private PlaylistBean playlistBean;
    @Inject
    private SongServiceBean songServiceBean;

    @Operation(
            summary = "Get playlists",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of playlists"),
            }
    )
    @GET
    public Response getPlaylists() {
        return Response.ok(playlistBean.getPlaylists()).build();
    }

    @Operation(
            summary = "Get playlist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Playlist with given ID"),
                    @ApiResponse(responseCode = "404", description = "Playlist with ID not found"),
            },
            parameters = {
                    @Parameter(name = "playlistId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("{playlistId}")
    public Response getPlaylistById(@PathParam("playlistId") int playlistId) {
        Playlist playlist = playlistBean.getPlaylist(playlistId);
        if (playlist == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(ResponseError.error404()).build();
        }

        for(PlaylistSong playlistSong : playlist.getPlaylistSongs()) {
            // This could be optimized by making a SongService endpoint that would accept a list of song IDs and
            // would return a list of songs (now it's a microservice call for each song)
            playlistSong.setSong(songServiceBean.getSong(playlistSong.getSongId()));
        }

        return Response.ok(playlist).build();
    }

    //

    @Operation(
            summary = "Create playlist",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Playlist created"),
                    @ApiResponse(responseCode = "409", description = "Conflict when trying to create playlist"),
            },
            parameters = {
                    @Parameter(name = "profileId", in = ParameterIn.PATH, required = true)
            },
            requestBody = @RequestBody(content = {@Content(schema = @Schema(implementation = Playlist.class))})
    )
    @POST
    @Path("{profileId}")
    public Response createPlaylist(@PathParam("profileId") int profileId, Playlist playlist) {
        playlist = playlistBean.insertPlaylist(profileId, playlist);
        if (playlist == null) {
            return Response.status(Response.Status.CONFLICT).entity(playlist).build();
        } else {
            return Response.status(Response.Status.CREATED).entity(playlist).build();
        }
    }

    @Operation(
            summary = "Update playlist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Playlist updated"),
                    @ApiResponse(responseCode = "404", description = "Playlist with ID does not exist")
            }
    )
    @PUT
    @Path("{playlistId}")
    public Response updatePlaylist(@PathParam("playlistId") int playlistId, Playlist playlist) {
        playlist = playlistBean.updatePlaylist(playlistId, playlist);

        if (playlist == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(playlist).build();
        }
    }

    @Operation(
            summary = "Delete playlist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Playlist deleted"),
                    @ApiResponse(responseCode = "404", description = "Playlist with ID does not exist")
            }
    )
    @DELETE
    @Path("{playlistId}")
    public Response deletePlaylist(@PathParam("playlistId") int playlistId) {

        boolean deleted = playlistBean.deletePlaylist(playlistId);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
