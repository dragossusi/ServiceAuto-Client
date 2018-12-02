package dragos.rachieru.serviceautoclient

import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * ServiceAutoClient by Imbecile Games
 *
 * @since 02.12.2018
 * @author Dragos
 */
interface ServiceClientApi {
    @POST("requests")
    fun sendIssue(@Body issueBody: IssueBody): Completable
}