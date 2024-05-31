package com.example.backendreactinicial.controllers;

import com.example.backendreactinicial.entities.CartItem;
import com.example.backendreactinicial.repositories.InstrumentoRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/mercadopago")
public class MercadoPagoController {

    @Value("${mercadopago.access_token}")
    private String accessToken;


    @PostMapping("/create-preference")
    public String createPreference(@RequestBody List<CartItem> cartItems) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(accessToken);



        List<PreferenceItemRequest> items = cartItems.stream().map(cartItem -> PreferenceItemRequest.builder()
                .title(cartItem.getInstrumento())
                .quantity(cartItem.getCantidad())
                .unitPrice(cartItem.getPrecio())
                .build()).collect(Collectors.toList());

        PreferencePayerRequest payer = PreferencePayerRequest.builder()
                .email("TESTUSER249672717")  // Email de prueba
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(payer)
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        return preference.getSandboxInitPoint();
    }
}
