/*
 * File: app/model/MachineModel.js
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

Ext.define('Peacock.model.MachineModel', {
    extend: 'Ext.data.Model',

    fields: [
        {
            name: 'machine_id',
            type: 'int'
        },
        {
            name: 'machine_mac_addr',
            type: 'string'
        },
        {
            name: 'machine_name',
            type: 'string'
        },
        {
            name: 'template',
            type: 'string'
        },
        {
            name: 'status',
            type: 'string'
        },
        {
            name: 'uptime',
            type: 'string'
        },
        {
            name: 'host_name',
            type: 'string'
        },
        {
            name: 'is_vm',
            type: 'boolean'
        },
        {
            name: 'os_name',
            type: 'string'
        },
        {
            name: 'os_ver',
            type: 'string'
        },
        {
            name: 'os_arch',
            type: 'string'
        },
        {
            name: 'cpu_clock',
            type: 'string'
        },
        {
            name: 'cpu_num',
            type: 'int'
        },
        {
            name: 'mem_size',
            type: 'string'
        },
        {
            name: 'ip_addr',
            type: 'string'
        },
        {
            name: 'register_dt',
            type: 'string'
        }
    ]
});