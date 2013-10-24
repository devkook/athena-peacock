/*
 * File: app/controller/VmTabPanelController.js
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

Ext.define('Peacock.controller.VmTabPanelController', {
    extend: 'Ext.app.Controller',

    views: [
        'VMTabPanel'
    ],

    onTabpanelTabChange: function(tabPanel, newCard, oldCard, eOpts) {

        var grid;

        if(newCard.title == "General"){

            this.viewGeneral();

        }else if(newCard.title == "Network Interfaces"){

            grid = Ext.getCmp('vmNicGrid');


            grid.getStore().load({
                params:{
                    vmId : Peacock.app.selectedRecord.get("vmId")
                }
            });


        }
    },

    onTabpanelAdded: function(component, container, pos, eOpts) {
        /*
        * 초기 화면 조회시. (목록에서 선택시)
        */

        this.viewGeneral();


    },

    viewGeneral: function() {
        /*
        * Virtual Machine General Tab 화면 조회.
        */

        var formPanel = Ext.getCmp("vmDescForm");

        formPanel.getForm().load({
            params : {
                vmId : Peacock.app.selectedRecord.get("vmId")
            },
            url : "rhevm/vms/info"
        });
    },

    init: function(application) {
        this.control({
            "vmTabPanel": {
                tabchange: this.onTabpanelTabChange
            },
            "tabpanel": {
                added: this.onTabpanelAdded
            }
        });
    }

});