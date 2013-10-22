/*
 * File: app/controller/InstanceTabPanelController.js
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

Ext.define('Peacock.controller.InstanceTabPanelController', {
    extend: 'Ext.app.Controller',

    views: [
        'InstanceTabPanel'
    ],

    onTabpanelTabChange: function(tabPanel, newCard, oldCard, eOpts) {

        var grid;

        if(newCard.title == "Description"){

            this.viewDescription();

        }else if(newCard.title == "Software"){

            grid = Ext.getCmp('instSoftGrid');


            //Ext.apply(grid.getStore().getProxy().extraParams, {
            //    id: selectedRecords[0].get("machine_id")
            //});

            grid.getStore().load({
                params:{
                    machineId : Peacock.app.selectedRecord.get("machineId")
                }
            });


        }else if(newCard.title == "OS Package"){


            grid = Ext.getCmp('instOSPkgGrid');


            grid.getStore().load({
                params:{
                    machineId : Peacock.app.selectedRecord.get("machineId")
                }
            });

        }else if(newCard.title == "Monitoring"){

            Ext.getStore('ChartIdleCPUStore').load({
                params:{
                    machineId : Peacock.app.selectedRecord.get("machineId"),
                    monFactorId : "FACTOR_001",
                    timeRange : "30m",
                    period : "1m"
                }
            });
            Ext.getStore('ChartCombCPUStore').load({
                params:{
                    machineId : Peacock.app.selectedRecord.get("machineId"),
                    monFactorId : "FACTOR_002",
                    timeRange : "30m",
                    period : "1m"
                }
            });
            Ext.getStore('ChartTotalMemStore').load({
                params:{
                    machineId : Peacock.app.selectedRecord.get("machineId"),
                    monFactorId : "FACTOR_003",
                    timeRange : "30m",
                    period : "1m"
                }
            });
            Ext.getStore('ChartFreeMemStore').load({
                params:{
                    machineId : Peacock.app.selectedRecord.get("machineId"),
                    monFactorId : "FACTOR_004",
                    timeRange : "30m",
                    period : "1m"
                }
            });
            Ext.getStore('ChartUsedMemStore').load({
                params:{
                    machineId : Peacock.app.selectedRecord.get("machineId"),
                    monFactorId : "FACTOR_005",
                    timeRange : "30m",
                    period : "1m"
                }
            });

        }
    },

    onTabpanelAdded: function(component, container, pos, eOpts) {
        /*
        * 초기 화면 조회시. (목록에서 선택시)
        */

        this.viewDescription();






    },

    viewDescription: function() {
        /*
        * Instance Descript Tab 화면 조회.
        */

        var formPanel = Ext.getCmp("instDescForm");

        formPanel.getForm().load({
            params : {
                machineId : Peacock.app.selectedRecord.get("machineId")
            },
            url : "machine/getMachine"
        });
    },

    init: function(application) {
        this.control({
            "instanceTabPanel": {
                tabchange: this.onTabpanelTabChange,
                added: this.onTabpanelAdded
            }
        });
    }

});
