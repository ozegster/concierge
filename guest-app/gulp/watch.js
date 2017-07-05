var gulp = require('gulp');
var conf = require('./conf');
var browserSync = require('browser-sync').get('browserSyncInstance');


gulp.task('watch',function(){
    gulp.watch(conf.paths.src + conf.paths.javascript,browserSync.reload);
    gulp.watch(conf.paths.src + conf.paths.html,browserSync.reload);
    gulp.watch(conf.paths.src + conf.paths.css,['style']);
});


