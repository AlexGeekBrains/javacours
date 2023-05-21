angular.module('app', []).controller('studentController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/student';

    $scope.loadStudents = function () {
        $http.get(contextPath + '/students')
            .then(function (response) {
                $scope.StudentList = response.data;
            });
    };

    $scope.deleteStudent = function (studentId) {
        $http.delete(contextPath + '/students/delete/' + studentId)
            .then(function (response) {
                $scope.loadStudents();
            });
    }
   $scope.updateMark = function (studentId, newMark) {
       console.log(studentId);
       console.log(newMark);
       $http.put(contextPath + '/students/update/' + studentId, parseInt(newMark))
           .then(function (response) {
               $scope.loadStudents();
           });
   };

    $scope.loadStudents();
});