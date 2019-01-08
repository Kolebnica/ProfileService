package api.resources;

import beans.crud.PlaylistSongBean;
import entities.PlaylistSong;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@Tags(value = @Tag(name = "playlist songs"))
public class PlaylistSongResource {

    @Inject
    private PlaylistSongBean playlistSongBean;

    @Operation(
            summary = "Add song to playlist",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Song added to playlist"),
                    @ApiResponse(responseCode = "400", description = "Error when trying to add song to playlist"),
                    @ApiResponse(responseCode = "409", description = "Song already on playlist"),
            },
            parameters = {
                    @Parameter(name = "playlistId", in = ParameterIn.PATH, required = true),
                    @Parameter(name = "songId", in = ParameterIn.PATH, required = true)
            }
    )
    @POST
    @Path("{playlistId}/add/{songId}")
    public Response addSongToPlaylist(@PathParam("playlistId") int playlistId, @PathParam("songId") int songId) {
        PlaylistSong ps;

        ps = playlistSongBean.getPlaylistSong(playlistId, songId);
        if (ps != null) {
            return Response.status(Response.Status.CONFLICT).entity(new ResponseError(409, "Song is already in this playlist.")).build();
        }

        ps = playlistSongBean.insertPlaylistSong(playlistId, songId);
        if (ps == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ps).build();
        } else {
            return Response.status(Response.Status.CREATED).entity(ps).build();
        }
    }

    @Operation(
            summary = "Remove song from playlist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Song removed from playlist"),
                    @ApiResponse(responseCode = "404", description = "Song with ID not on playlist with ID")
            }
    )
    @DELETE
    @Path("{playlistId}/remove/{songId}")
    public Response deleteProfile(@PathParam("playlistId") int playlistId, @PathParam("songId") int songId) {

        boolean deleted = playlistSongBean.deletePlaylistSong(playlistId, songId);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
