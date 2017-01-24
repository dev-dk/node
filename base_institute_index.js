var express = require('express');
var router = express.Router();
var newsCtrl = require('../controllers/news_ctrl');
var newsDCtrl = require('../controllers/newsdctrl');
var authCtrl = require('../controllers/auth_ctrl');
var purchaseCtrl = require('../controllers/purchase_ctrl');
var quizCtrl = require('../controllers/quiz_ctrl');
var cors = require('cors');

var postOptions = {
    "allowedHeaders":"Origin, X-Requested-With, ContentType, Accept"
};

router.get('/getNewsId',newsCtrl.getNewsId);
router.post('/saveNews', newsCtrl.saveNews);
router.get('/getNews', newsCtrl.getNews);
router.post('/register', authCtrl.register);
router.post('/login', authCtrl.login);
router.post('/sendBugReport', authCtrl.sendBugReport);
router.get('/getAllNews', newsCtrl.getAllNews);
router.get('/postComments', newsCtrl.postComments);
router.get('/getListOfMaterials', newsCtrl.getListOfMaterials);
router.get('/getS3FreeMaterials', newsCtrl.getS3FreeMaterials);
router.post('/updatePurchase', purchaseCtrl.updatePurchase);
router.get('/validatePurchase', purchaseCtrl.validatePurchase);
router.get('/getPrepMaterials', newsCtrl.getPrepMaterials);
router.get('/getSC', purchaseCtrl.getSC);
router.get('/test', newsCtrl.test);

router.get('/getNEWSDID',cors(), newsDCtrl.getNEWSDID);
router.get('/getAllNewsD',cors(), newsDCtrl.getAllNewsD);
router.get('/searchHeading',cors(), newsDCtrl.searchHeading);
router.get('/removeNews',cors(), newsDCtrl.removeNews);
router.post('/saveNewsD',cors(), newsDCtrl.saveNewsD);

router.get('/getQuizId',cors(), quizCtrl.getQuizId);
router.get('/getAllQuizNames',cors(), quizCtrl.getAllQuizNames);
router.post('/saveQuizPart',cors(postOptions), quizCtrl.saveQuizPart);
router.post('/saveQuestionPart',cors(postOptions), quizCtrl.saveQuestionPart);
router.get('/getContentId', cors(), quizCtrl.getContentId);
router.get('/getQuestionId', cors(), quizCtrl.getQuestionId);
router.get('/getQuizById', cors(), quizCtrl.getQuizById);
router.post('/submitQuiz', quizCtrl.submitQuiz);
router.get('/getUserQuizRanking', cors(), quizCtrl.getUserQuizRanking);
router.get('/deleteQuiz', cors(), quizCtrl.deleteQuiz);
router.get('/deleteQuestion', cors(), quizCtrl.deleteQuestion);
router.get('/updatePublishFlag', cors(), quizCtrl.updatePublishFlag);

module.exports = router;
