package grub.services;

import grub.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteService {
    @Autowired
    SiteRepository siteRepository;
}
