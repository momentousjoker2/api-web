<?php
require_once './DB_Connect.php';
require_once './Empleados.php';
class DB
{ 
    private $db;
    private $pdo;

    function __construct() 
    {
        $this->db = new DB_Connect();
        $this->pdo = $this->db->connect();
    }
 
    function __destruct() { }
 

    public function getAllEmpleados()
    {
        $stmt = $this->pdo->prepare('SELECT * FROM empleados');
        $stmt->execute();
        $array = array();
       
        $ind = 0;
        foreach ($stmt as $row) 
        {
            // do something with $row
            $itm = new Empleados();  //obtenemos los campos del models
            $itm->Clave= $row['Clave'];
            $itm->Apellidos = $row['Apellidos'];
            $itm->Nombres = $row['Nombres'];
            $itm->Puesto = $row['Puesto'];
            $itm->Salario = $row['Salario'];
            $array[$ind] = $itm;
            $ind++;
        }

        return $array;
    }


        public function getEmpleado($id)
        {
            $stmt = $this->pdo->prepare('SELECT * from empleados where Clave = :Clave ');
            $stmt->execute( array('Clave' => $id));
            $array = array();

            $ind = 0;
            foreach ($stmt as $row) 
            {
                $itm = new Empleados();
                $itm->Clave= $row['Clave'];
                $itm->Apellidos = $row['Apellidos'];
                $itm->Nombres = $row['Nombres'];
                $itm->Puesto = $row['Puesto'];
                $itm->Salario = $row['Salario'];
                $array[$ind] = $itm;
                $ind++;
            }
            return $array;
         }

         public function insert($Clave,$Apellidos,$Nombres,$Puesto,$Salario)
        {
            $stmt=$this->pdo->prepare("INSERT INTO empleados (Clave,Apellidos, Nombres, Puesto, Salario) VALUES (:Clave,:Apellidos, :Nombres, :Puesto, :Salario)");
            $stmt->bindParam(':Clave',$Clave,PDO::PARAM_INT);
            $stmt->bindParam(':Apellidos',$Apellidos,PDO::PARAM_STR, 50);
            $stmt->bindParam(':Nombres',$Nombres,PDO::PARAM_STR,50);
            $stmt->bindParam(':Puesto',$Puesto,PDO::PARAM_STR,50);
            $stmt->bindParam(':Salario',$Salario,PDO::PARAM_STR);
            $stmt->execute();
            $lastInsertId = $this->pdo->lastInsertId();
            return $lastInsertId;
        }

        public function update($Clave, $Apellidos,$Nombres,$Puesto,$Salario)
        {
            $stmt=$this->pdo->prepare("UPDATE empleados SET Apellidos=:Apellidos, Nombres=:Nombres, Puesto=:Puesto, Salario=:Salario WHERE Clave=:Clave");
            $stmt->bindParam(':Clave',$Clave,PDO::PARAM_INT);
            $stmt->bindParam(':Apellidos',$Apellidos,PDO::PARAM_STR, 50);
            $stmt->bindParam(':Nombres',$Nombres,PDO::PARAM_STR,50);
            $stmt->bindParam(':Puesto',$Puesto,PDO::PARAM_STR,50);
            $stmt->bindParam(':Salario',$Salario,PDO::PARAM_STR);
            $r = $stmt->execute();
            return $r;
        }

        public function delete($Clave)
        {
            $stmt = $this->pdo->prepare("DELETE FROM empleados WHERE Clave = :Clave ; ");
            $stmt->bindParam(':Clave', $Clave,PDO::PARAM_INT);
            $r = $stmt->execute();
            return $r;
    }


}
 
?>