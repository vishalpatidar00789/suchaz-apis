package com.suchaz.app.cucumber.stepdefs;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.suchaz.app.SuchazapisApp;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = SuchazapisApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
