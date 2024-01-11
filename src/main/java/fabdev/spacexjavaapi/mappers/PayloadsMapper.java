package fabdev.spacexjavaapi.mappers;

import fabdev.spacexjavaapi.DTOs.PayloadsDTO;
import fabdev.spacexjavaapi.models.Payloads;

import java.util.List;
import java.util.stream.Collectors;

public class PayloadsMapper {
    public static List<Payloads> mapPayloadsDTOToPayloads(List<PayloadsDTO> payloadsDTOList){
        return payloadsDTOList.stream()
                .map(payloadsDTO ->
                        new Payloads(
                                payloadsDTO.customers(),
                                payloadsDTO.id()))
                .collect(Collectors.toList());
    }
}
