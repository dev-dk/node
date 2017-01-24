var express = require('express');
var router = express.Router();
var mainPageCtrl = require('../controllers/main_page_ctrl');
var authCtrl = require('../controllers/auth_ctrl');
var quizCtrl = require('../controllers/quiz_ctrl');

router.get('/getMainPageContent', mainPageCtrl.getMainPageContent);
router.get('/getMoviesPageContent', mainPageCtrl.getMoviesPageContent);
router.get('/getMovieWithNews', mainPageCtrl.getMovieWithNews);
router.get('/getAdFile', mainPageCtrl.getAdFile);
router.get('/getNews', mainPageCtrl.getNews);
router.post('/sendBugReport', mainPageCtrl.sendBugReport);
router.get('/test', quizCtrl.getSQL);
router.post('/register', authCtrl.register);
router.post('/submitQuiz', quizCtrl.submitQuiz);
router.get('/saveMovieRating', quizCtrl.saveMovieRating);
router.get('/getWinnersList', quizCtrl.getWinnersList);
router.get('/updateFCMToken', authCtrl.updateFCMToken);
module.exports = router;
