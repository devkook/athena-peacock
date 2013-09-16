/*
 * File: app/model/UsersModel.js
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

Ext.define('Peacock.model.UsersModel', {
    extend: 'Ext.data.Model',

    fields: [
        {
            name: 'user_id',
            type: 'string'
        },
        {
            name: 'user_name',
            type: 'string'
        },
        {
            name: 'groups',
            type: 'int'
        },
        {
            name: 'password',
            type: 'string'
        },
        {
            name: 'reg_dt',
            type: 'string'
        },
        {
            name: 'role_id',
            type: 'int'
        },
        {
            name: 'login_id',
            type: 'string'
        },
        {
            name: 'dept_name',
            type: 'string'
        },
        {
            name: 'email',
            type: 'string'
        },
        {
            name: 'is_admin',
            type: 'boolean'
        },
        {
            name: 'status',
            type: 'int'
        },
        {
            name: 'last_logon',
            type: 'string'
        },
        {
            name: 'reg_user_id',
            type: 'int'
        },
        {
            name: 'upd_user_id',
            type: 'int'
        },
        {
            name: 'upd_dt',
            type: 'string'
        }
    ]
});