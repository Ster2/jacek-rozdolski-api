package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.naming.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://ster2.github.io/");
        context.setVariable("button", "Visit Website");
        context.setVariable("goodbye_message", "Goodbye! Have a nice day!");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("show_button", false);
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("created-trello-card-mail", context);
    }