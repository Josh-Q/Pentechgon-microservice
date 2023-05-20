package helloworld.api.constants;

public class Constants {
    public enum Secrets {
        KEY("pMBzEc2a504+tNWz8UjKWMvckl9fEI41c/7jqYSzE2c=");

        private final String secretKey;

        Secrets(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getSecretKey() {
            return secretKey;
        }
    }

}
