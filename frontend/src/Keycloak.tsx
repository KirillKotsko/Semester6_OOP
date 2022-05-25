import Keycloak from "keycloak-js";

const keycloak = Keycloak({
    url: "http://localhost:8000",
    realm: "realm",
    clientId: "client"
});

export default keycloak;
