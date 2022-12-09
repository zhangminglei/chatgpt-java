package gg.acai.chatgpt;

/**
 * © Acai Software - All Rights Reserved
 * @author Kaiser
 * @since 08.12.2022 18:33
 */
public class StandardMessage implements Message {
    private final String id;
    private final String role;
    private final Content content;

    StandardMessage(MessageBuilder builder) {
        this.id = builder.id;
        this.role = builder.role;
        this.content = builder.content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public Content getContent() {
        return content;
    }

    public static class MessageBuilder {
        private String id;
        private String role;
        private Content content;

        public MessageBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public MessageBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public MessageBuilder setContent(Content content) {
            this.content = content;
            return this;
        }

        public StandardMessage build() {
            return new StandardMessage(this);
        }
    }
}