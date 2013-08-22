/*
 * File: app/controller/MainGridController.js
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

Ext.define('Peacock.controller.MainGridController', {
    extend: 'Ext.app.Controller',

    views: [
        'InstanceTabPanel',
        'UserTabPanel',
        'UserGroupTabPanel'
    ],

    onGridpanelSelect: function(rowmodel, record, index, eOpts) {


        /*
        * main grid 에서 한 row 선택시 처리 (상세정보 tab 정보 조회)
        */

        Peacock.app.debug("MainGridController.onGridpanelSelect.");
        Peacock.app.selectedRecord = record;



        Ext.getCmp("tbActionMenu").setDisabled(false);



        var modelName = Ext.getClassName(record);
        //alert(modelName);
        var detailPanel = Ext.getCmp('detailPanel');
        var tabPanel;

        detailPanel.removeAll(true);

        if(modelName.indexOf("MachineModel") > -1){

            //tabPanel = Ext.widget('instanceTabPanel');
            tabPanel = this.getInstanceTabPanelView();


        }else if(modelName.indexOf("UserGroupModel") > -1){

            tabPanel = this.getUserGroupTabPanelView();

        }else if(modelName.indexOf("UsersModel") > -1){


            tabPanel = this.getUserTabPanelView();

        }


        detailPanel.add(tabPanel);
        detailPanel.layout.setActiveItem(0);

        //tabPanel.selectedRecord = record;
        //alert(tabPanel.selectedRecord.get("machine_id"));
    },

    onGridpanelReconfigure: function(gridpanel, store, columns, oldStore, oldColumns, eOpts) {
        /*
        * mainGridPanel 이 새로운 메뉴화면으로 갱신될때 detailPanel 도 초기화.
        */
        Peacock.app.debug("MainGridController.onGridpanelReconfigure");


        var detailPanel = Ext.getCmp('detailPanel');
        detailPanel.removeAll(true);
    },

    init: function(application) {
        this.control({
            "#mainGridPanel": {
                select: this.onGridpanelSelect
            },
            "gridpanel": {
                reconfigure: this.onGridpanelReconfigure
            }
        });
    }

});
