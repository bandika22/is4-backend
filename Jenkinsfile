
node{

   def tomcatWeb = 'C:\\apache-tomcat-9.0.53\\webapps'
   def tomcatBin = 'C:\\apache-tomcat-9.0.53\\bin'
   def tomcatStatus = ''
   stage('SCM Checkout'){
     git 'https://github.com/bandika22/is4-backend.git'
   }
   stage('Compile-Package-create-war-file'){
      // Get maven home path
      def mvnHome =  tool name: 'maven-3', type: 'maven'   
      bat "${mvnHome}/bin/mvn package"
      }
   stage('Deploy to Tomcat'){
     bat "${tomcatBin}\\shutdown.bat"
     bat "copy target\\is4war.war \"${tomcatWeb}\\is4war.war\""
   }
      stage ('Start Tomcat Server') {
         sleep(time:5,unit:"SECONDS") 
         bat "${tomcatBin}\\startup.bat"
         sleep(time:100,unit:"SECONDS")
   }
}

