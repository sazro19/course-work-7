package com.vedverma.spring.security.parking.services;

import com.fasterxml.jackson.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "amount",
        "clientIdentifier",
        "time",
        "lotId",
        "parkingId",
        "formatted"
})
public class ClientLog {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("clientIdentifier")
    private String clientIdentifier;

    @JsonProperty("time")
    private LocalDateTime time;

    @JsonProperty("lotId")
    private UUID lotId;

    @JsonProperty("parkingId")
    private UUID parkingId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = time.format(formatter);
        this.formatted = formatDateTime;
    }

    public UUID getLotId() {
        return lotId;
    }

    public void setLotId(UUID lotId) {
        this.lotId = lotId;
    }

    public UUID getParkingId() {
        return parkingId;
    }

    public void setParkingId(UUID parkingId) {
        this.parkingId = parkingId;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }

    public void setClientIdentifier(String clientIdentifier) {
        this.clientIdentifier = clientIdentifier;
    }

    @JsonProperty("formatted")
    private String formatted;

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }
}
