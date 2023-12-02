package bankmicroservicesapp.controller;

import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.service.AgreementService;
import bankmicroservicesapp.validation.annotation.ValidUUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agreement")
public class AgreementController {
    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@ValidUUID @PathVariable("id") String id) {
        agreementService.deleteById(id);
        throw new ResponseStatusException(HttpStatus.OK);
    }

    @RequestMapping("/get/{id}")
    public AgreementDto getById(@ValidUUID @PathVariable("id") UUID id) {
        return agreementService.findAgreementById(id);
    }

    @GetMapping("/get/by-manager")
    public List<AgreementDto> findAgreementManager(@ValidUUID @RequestParam(name = "id") UUID id) {
        return agreementService.findAgreementWhereManagerId(id);
    }

    @GetMapping("/get/by-client")
    public List<AgreementDto> findAgreementsClientIdIs(@ValidUUID @RequestParam(name = "id") UUID id) {
        return agreementService.findAgreementsWhereClientIdIs(id);
    }
}
