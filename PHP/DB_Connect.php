<?php
 
class DB_Connect {
 
    // constructor
    function __construct() 
    {
         
    }
 
    // destructor
    function __destruct() 
    {
        // $this->close();
    }
 
    // Connecting to database
    public function connect() 
    {
        try {
            $con = new PDO('mysql:host=mysql-momentousjoker2.alwaysdata.net;dbname=momentousjoker2_api', '219364_momentous', 'abPHA_E?+jkl', null);
            $con->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
            $con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (PDOException $e) {
        print "¡Error!: " . $e->getMessage() . "<br/>";
        die();
    }
        // return database handler
        return $con;
    }
 
    // Closing database connection
    public function close() 
    {
        // mysql_close();
    }
 
}
 
?>