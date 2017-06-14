var gulp = require('gulp');
var browserSync = require('browser-sync').create();
var conf = require('./conf');

gulp.task("openBrowser", function(){
    browserSync.init({
        port : 8000,
        server: {
            baseDir: [
                conf.paths.tmp,
                conf.paths.src
            ],
            routes : {'/bower_components': 'bower_components'}
        },
    });
});