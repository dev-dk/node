//credits
var creditsource = 10;
//operations
var activator = require('../director/operations/verifier.js');
var apecheck = require('../director/operations/ape_check.js');
var addCount = require('../director/operations/add_ad_count.js');
var addGCount = require('../director/operations/add_g_ad_count.js');
var dapecheck = require('../director/operations/d_ape_check.js');
//collections
var user_index = require('../director/mongohelper/mongo_userindex.js');
var movieindex = require('../director/mongohelper/mongo_movie_index.js');
var newsindex = require('../director/mongohelper/mongo_news_index.js');
var movie_data = require('../director/mongohelper/mongo_movie_data.js');
var notificaitons = require('../director/mongohelper/mongo_notifications.js');
var user_data = require('../director/mongohelper/mongo_user_data.js');
var userbackup = require('../director/mongohelper/mongo_allusers.js');
var refnodes = require('../director/mongohelper/mongo_refnodes.js');
var mrefnodes = require('../director/mongohelper/mongo_mrefnodes.js');
var srcnodes = require('../director/mongohelper/mongo_useraccounts.js');
var toprefes = require('../director/mongohelper/mongo_feb_referral.js');
var allcredits = require('../director/mongohelper/mongo_mov_referral.js');
var movcredit = require('../director/mongohelper/mongo_mov_credits.js');
var topmovrefes = require('../director/mongohelper/mongo_topmov_referral.js');

//greentrends
var greentrends = require('../director/mongohelper/mongo_greentrends.js');

//modules
var zlib = require('zlib');
var promise = require('bluebird');
var fs = require('fs');
var _ = require('underscore');
var path           = require('path')
    , template_dir   = path.resolve(__dirname, '..', 'templates')
    , emailTemplates = require('email-templates')
    , nodemailer     = require('nodemailer');
var gcm = require('node-gcm');
var AWS = require('aws-sdk');
// AWS.config.loadFromPath('./config.json');
promise.promisifyAll(fs);

module.exports = function (app) {

    app.get('/', function (req, res) {
        res.json({
            message: "Initiating"
        });
    });

    //Prime
    app.post('/Prime', function (req, res) {});

    //Vericon
    app.post('/Vericon',function(req,res) {});

    //Qcon
    app.post('/Qcon',function(req,res) {});

    app.post('/verifier', function (req, res) {});
    
    app.post("/ApeUiUdMdMi", function (req, res) {
    });

    app.post("/d_ApeUiUdMdMi", function (req, res) {

    });

    app.get('/ac',function(req,res){
    });

    app.get('/acm', function (req, res) {
    });

    app.post('/deleterqst',function(req,res) {

    });

    app.post('/get_movie',function(req,res) {
    });

    //History
    app.post('/History',function(req,res) {
    });

    app.get('/nox',function(req,res) {
    });

    app.post('/regToken',function(req,res) {
    });

    app.get('/firewings',function(req,res) {
    });

    app.post('/setNotiFreq',function(req,res) {
    });

    app.get('/getAdFile',function(req,res) {
    });

    app.post('/referation', function(req,res){
    });

    app.post('/sharmion', function(req,res){
    });	

    app.post('/reDeem', function (req, res) {
    });

    app.get('/getRAmount', function (req, res) {
    });

    app.get('/slmidpush',function(req,res) {
    });

    app.get('/getNews',function(req,res) {
    });

    app.get('/getAllNews',function(req,res) {
    });

    app.get('/getTagNews',function(req,res) {
    });

    app.post('/updateCount', function (req, res) {
    });

    app.post('/updateGalleryStatus', function (req, res) {
    });

    app.get('/getUpcoming',function(req,res) {
    });

    app.get('/getPasscode',function(req,res) {
    });

    app.get('/searchNews', function (req, res) {
    });

    app.get('/getUserStatus', function (req, res) {
    });

    app.get('/getTopReferrals', function (req, res) {
    });

    app.get('/getTopMovReferrals', function (req, res) {
    });

    app.get('/getReferralHistory',function(req,res) {
    });

    app.get('/getMovRefHistory',function(req,res) {
    });

    app.get('/getAllPoints', function (req, res) {
    });

    app.get('/test', function (req, res) {
    });

    app.get('/getAllMovies',function(req,res) {
    });

    //DEMO MODULE FOR ADISRI FASHIONS ----------------------------------------------------------------------------------
    var demodata = require('../director/mongohelper/demodata.js');
   
    app.get('/getList', function (req, res) {
    });

    app.post('/postOrder', function (req, res) {
    });

    app.post('/cancelOrder', function (req, res) {
    });

    app.get('/getAgg', function (req, res) {
    });

    //DEMO MODULE FOR GREEN TRENDS--------------------------------------------------------------------------------------

    app.post('/saveToken', function (req, res) {
    });

    app.get('/pushNotification', function (req, res) {
    });

    app.post('/sendEmail', function (req, res) {
    });


    app.post('/sendBugReport',function (req, res) {
    });
};
