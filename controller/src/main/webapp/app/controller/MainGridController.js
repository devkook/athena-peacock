/*
 * File: app/controller/MainGridController.js
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

Ext.define('Peacock.controller.MainGridController', {
    extend: 'Ext.app.Controller',

    views: [
        'InstanceTabPanel',
        'UserTabPanel',
        'UserGroupTabPanel',
        'ASGroupTabPanel'
    ],

    onGridpanelSelect: function(rowmodel, record, index, eOpts) {


        /*
        * main grid 에서 한 row 선택시 처리 (상세정보 tab 정보 조회)
        */

        Peacock.app.debug("MainGridController.onGridpanelSelect.");
        Peacock.app.selectedRecord = record;



        Ext.getCmp("tbActionMenu").setDisabled(false);


        var detailPanel = Ext.getCmp('detailPanel');
        detailPanel.removeAll(true);


        var tabPanel;
        var detailTitleLabel = Ext.getCmp('detailTitleLabel');


        if (Peacock.app.menu_id == 'insts'){

            //tabPanel = Ext.widget('instanceTabPanel');
            tabPanel = this.getInstanceTabPanelView();
            detailTitleLabel.setText(record.get("os_name"));

        }else if (Peacock.app.menu_id == 'img-tmp'){


        }else if (Peacock.app.menu_id == 'img-pkg'){


        }else if (Peacock.app.menu_id == 'scal-grp'){

            tabPanel = this.getASGroupTabPanelView();
            detailTitleLabel.setText(record.get("as_group_name"));


        }else if (Peacock.app.menu_id == 'scal-lb'){


        }else if (Peacock.app.menu_id == 'user-grp'){

            tabPanel = this.getUserGroupTabPanelView();
            detailTitleLabel.setText(record.get("group_name"));

        }else if (Peacock.app.menu_id == 'users'){

            tabPanel = this.getUserTabPanelView();
            detailTitleLabel.setText(record.get("user_name"));
        }


        detailPanel.add(tabPanel);
        detailPanel.layout.setActiveItem(0);

    },

    init: function(application) {
        this.control({
            "#mainGridPanel": {
                select: this.onGridpanelSelect
            }
        });
    }

});
