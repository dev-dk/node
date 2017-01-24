var express = require('express');
var router = express.Router();
var ctrlAuth = require('../controllers/auth_ctrl');
var ctrlTest = require('../controllers/test_ctrl');
var ctrlTrip = require('../controllers/trip_ctrl');
var ctrlDriv = require('../controllers/driv_ctrl');
var ctrlSugg = require('../controllers/sugg_ctrl');
var ctrlTruck = require('../controllers/truck_ctrl');
var ctrlDayBook = require('../controllers/daybook_ctrl');
var ctrlInvoice = require('../controllers/invoice_ctrl');

router.post('/saveTripSheet', ctrlTrip.saveTripSheet);

router.post('/saveHiredTripSheet', ctrlTrip.saveHiredTripSheet);

router.get('/getTripSheet', ctrlTrip.getById);

router.get('/getAllTripSheet', ctrlTrip.getAll);

router.get('/getTripReport', ctrlTrip.getTripReport);

router.post('/saveDriver', ctrlDriv.save);

router.post('/updateDriver', ctrlDriv.updateDriver);

router.get('/getDriver', ctrlDriv.getById);

router.get('/getAllDrivers', ctrlDriv.getAll);

router.get('/getAllSugg', ctrlSugg.getAll);

router.get('/login', ctrlAuth.login);

router.get('/updatePermissionFlag', ctrlAuth.updatePermissionFlag);

router.get('/pinCheck', ctrlAuth.pinCheck);

router.post('/saveTruck', ctrlTruck.save);

router.post('/updateTruck', ctrlTruck.updateTruck);

router.get('/getTruck', ctrlTruck.getById);

router.get('/getAllTrucks', ctrlTruck.getAll);

router.get('/test', ctrlTest.testingIn);

router.get('/getDayBook', ctrlDayBook.getDayBook);

router.post('/updateDayBookReceipts', ctrlDayBook.updateDayBookReceipts);

router.get('/getDayBookReport', ctrlDayBook.getDayBookReport);

router.post('/sendBugReport', ctrlAuth.sendBugReport);

router.get('/getAllUsers', ctrlAuth.getAllUsers);

router.post('/updateUserPermissions', ctrlAuth.updateUserPermissions);

router.get('/updateCurrentBalance', ctrlDriv.updateBalance);

router.get('/getJourneysForInvoice', ctrlInvoice.getJourneysForInvoice);

router.post('/generateInvoice', ctrlInvoice.generateInvoice);

router.get('/getAllInvoice', ctrlInvoice.getAllInvoice);

router.get('/getInvoice', ctrlInvoice.getInvoice);

module.exports = router;
