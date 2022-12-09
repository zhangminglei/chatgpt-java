package gg.acai.chatgpt;

import gg.acai.acava.scheduler.AsyncPlaceholder;
import gg.acai.acava.scheduler.Schedulers;
import gg.acai.chatgpt.request.ChatGPTRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.UUID;

/**
 * © Acai Software - All Rights Reserved
 * @author Clouke
 * @since 09.12.2022 18:04
 */
public class AbstractConversation implements Conversation {

    private final UUID uuid;

    public AbstractConversation(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public Response sendMessage(ChatGPTRequest request) {
        request.setConversationId(this.uuid);

        HttpResponse<String> httpResponse = Unirest.post(APIUrls.CONVERSATION_URL.getUrl())
                .header("Authorization", "Bearer " + ChatGPTAPI.getInstance().getAccessToken())
                .body(request)
                .asString();

        return httpResponse::getBody;
    }

    @Override
    public AsyncPlaceholder<Response> sendMessageAsync(ChatGPTRequest request) {
        return Schedulers.supplyAsync(() -> sendMessage(request));
    }
}
