package bankmicroservicesapp.controller;

import bankmicroservicesapp.entity.Agreement;
import bankmicroservicesapp.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        try {
            agreementService.deleteById(agreementsId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.OK);
    }

//    @GetMapping("/get/agreementManager")
//    public List<Agreement> findAgreementManager(String managerId){
//
//    }
}
