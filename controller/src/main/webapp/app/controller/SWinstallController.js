/*
 * File: app/controller/SWinstallController.js
 *
 * This file was generated by Sencha Architect version 2.2.3.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Peacock.controller.SWinstallController', {
    extend: 'Ext.app.Controller',

    onSoftwareComboboxChange: function(field, newValue, oldValue, eOpts) {
        Peacock.app.debug("SWInstallController.onSoftwareComboboxChange. newValue = " + newValue);

        var frmId = null;

        if(newValue == 1){
            frmId = "apache";

        }else if(newValue == 2){
            frmId = "mysql";

        }else if(newValue == 3){
            frmId = "jboss";

        }else if(newValue == 4){
            frmId = "tomcat";


        }

        //var paramPanel = this.getParamPanel();
        var paramPanel = Ext.getCmp("swParamPanel");
        paramPanel.layout.setActiveItem(frmId);
    },

    init: function(application) {
        this.control({
            "#softwareCombo": {
                change: this.onSoftwareComboboxChange
            }
        });
    }

});
