/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.AppSettings.ALERT_TYPE;
import eapli.ecafeteria.application.ConfigurateKitchenAlertController;
import eapli.ecafeteria.application.ConfigurateUserAlertsLimitController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.io.Console;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diana Silva (!151088@isep.ipp.pt)
 */
public class ConfigurateKitchenAlertsLimitsUI extends AbstractUI{
    
    private final ConfigurateKitchenAlertController theController = new ConfigurateKitchenAlertController();

    @Override
    protected boolean doShow() {
        boolean flag = false;
        int type=0;
        while(!flag){
            try{
                type = Console.readInteger("Alert's type (1- red; 2- yellow): ");
                if(type==1 || type==2) flag=true;
            }catch(NumberFormatException r){
                System.out.println("You must insert 1 or 2.");
            }
        }
        
        flag=false;
        int limit=0;
        
        while(!flag){
            try{
                
                limit = Console.readInteger("Alert Limit: ");
                if(limit>0) {
                    flag=true;
                }
                
            }catch(NumberFormatException r){
                System.out.println("You must insert nr > 0.");
            }
        }
       
        try {
            if(type==1) theController.changeRedAlertLimit(limit);
            if(type==2) theController.changeYellowAlertLimit(limit);
            System.out.println("User alert limit change sucessfully.");
        } catch (IOException ex) {
            Logger.getLogger(ConfigurateUserAlertsLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String headline() {
        return "Configurate Kitchen Avalable Meals Alert Limits";
    }

}
