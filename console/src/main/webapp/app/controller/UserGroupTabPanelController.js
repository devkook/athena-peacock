/*
 * File: app/controller/UserGroupTabPanelController.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
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

Ext.define('Peacock.controller.UserGroupTabPanelController', {
    extend: 'Ext.app.Controller',

    views: [
        'UserGroupTabPanel'
    ],

    onTabpanelAdded: function(component, container, pos, eOpts) {
        this.viewUsers();
    },

    onTabpanelTabChange: function(tabPanel, newCard, oldCard, eOpts) {
        if(newCard.title == "Users"){

            this.viewUsers();

        }else if(newCard.title == "Summary"){


            /*
            * Groups > Summary Tab 화면 조회.
            */

            var grid1 = Ext.getCmp("groupSummaryGrid");

            var jsonObj;

            Ext.Ajax.request({
                url: 'static/groupSummary.json',
                params: {
                    group_id : Peacock.app.selectedRecord.get("group_id")
                },
                success: function(response){
                    var jsonObj = Ext.JSON.decode(response.responseText);

                    grid1.setSource(jsonObj.data);
                }
            });


        }
    },

    viewUsers: function() {

        /*
        * Groups > Users Tab 화면 조회.
        */

        var grid = Ext.getCmp("groupUsersGrid");

        grid.getStore().load({
            params : {
                group_id : Peacock.app.selectedRecord.get("group_id")
            }
        });
    },

    init: function(application) {
        this.control({
            "userGroupTabPanel": {
                added: this.onTabpanelAdded,
                tabchange: this.onTabpanelTabChange
            }
        });
    }

});
