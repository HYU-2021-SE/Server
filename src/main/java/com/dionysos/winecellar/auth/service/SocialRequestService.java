package com.dionysos.winecellar.auth.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dionysos.winecellar.auth.dto.LoginDto;
import com.dionysos.winecellar.auth.dto.SocialAccessTokenDto;

@Service
public class SocialRequestService {
    public final String redirectUri;
    public final String clientId;
    private final HttpClient client = HttpClient.newHttpClient();

    public SocialRequestService(
        @Value("${auth.redirect.url}") String redirectUri,
        @Value("${kakao.client.id}") String clientId
    ) {
        this.redirectUri = redirectUri;
        this.clientId = clientId;
    }

    public void requestAuthCode(HttpServletResponse httpServletResponse) throws IOException {
        String uri =
            "https://kauth.kakao.com/oauth/authorize?client_id=" + clientId + "&redirect_uri=" + redirectUri
                + "&response_type=code";
        httpServletResponse.sendRedirect(uri);
    }

    public SocialAccessTokenDto requestSocialAccessToken(String code) throws IOException, InterruptedException {
        String uri = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=" + clientId
            + "&redirect_uri=" + redirectUri + "&code=" + code;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .POST(HttpRequest.BodyPublishers.noBody())
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject object = new JSONObject(response.body());
        return new SocialAccessTokenDto(object.getString("access_token"));
    }

    public LoginDto requestSocialUser(SocialAccessTokenDto socialAccessTokenResponseDto) throws
        IOException,
        InterruptedException {
        String uri = "https://kapi.kakao.com/v2/user/me";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("Authorization", "Bearer " + socialAccessTokenResponseDto.getAccessToken())
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject object = new JSONObject(response.body());
        JSONObject account = object.getJSONObject("kakao_account");
        return new LoginDto(account.getString("email"));
    }
}
