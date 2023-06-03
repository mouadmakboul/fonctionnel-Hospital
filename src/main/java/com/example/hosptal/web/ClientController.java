package com.example.hosptal.web;

import com.example.hosptal.entities.ClientEntity;
import com.example.hosptal.entities.Patient;
import com.example.hosptal.repository.ClientRepository;
import com.example.hosptal.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "/index")
    public String index(Model model ,
                        @RequestParam(name = "page", defaultValue = "0") int page ,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<ClientEntity> clientEntityPage= clientRepository.findByNomContains(kw,PageRequest.of(page,size));
        model.addAttribute("listClients",clientEntityPage.getContent());
        model.addAttribute("pages",new int[clientEntityPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", kw);
        return "clients";
    }
}

