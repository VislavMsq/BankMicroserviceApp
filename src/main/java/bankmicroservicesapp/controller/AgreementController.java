package bankmicroservicesapp.controller;

import bankmicroservicesapp.controller.util.Valid;
import bankmicroservicesapp.dto.AgreementDto;
import bankmicroservicesapp.exeption.ErrorMessage;
import bankmicroservicesapp.exeption.InvalidIdException;
import bankmicroservicesapp.exeption.UserNotExistException;
import bankmicroservicesapp.service.AgreementService;
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

    @RequestMapping(value = "/delete/{agreementsId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("agreementsId") String agreementsId) {
        agreementService.deleteById(agreementsId);
        throw new ResponseStatusException(HttpStatus.OK);
    }

    @GetMapping("/get/agreementManager")
    public List<AgreementDto> findAgreementManager(@RequestParam(name = "managerId") UUID managerId) {
        return agreementService.findAgreementWhereManagerId(managerId);
    }

    @GetMapping("/get/agreementClientId")
    public List<AgreementDto> findAgreementsClientIdIs(@RequestParam(name = "clientId") UUID clientId) {
        if (!Valid.isValidUUID(clientId.toString())) {
            throw new InvalidIdException(ErrorMessage.INVALID_ID);
        }
        return agreementService.findAgreementsWhereClientIdIs(clientId);
    }
}
