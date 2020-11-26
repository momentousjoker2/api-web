<?php
require_once('DB.php');

class EmpleadoAPI
{
    public function API()
    {
        header('Content-Type: application/JSON');
        $method = $_SERVER['REQUEST_METHOD'];
        switch ($method) {
            case 'GET':
                $this->getEmpleados();
                break;
            case 'POST':
                $this->saveEmpleados();
                break;
            case 'PUT':
                $this->updateEmpleados();
                break;
            case 'DELETE':
                $this->deleteEmpleado();
                break;
            default:
                echo 'METODO NO SOPORTADO';
                break;
        }
    }


    function response($code=200, $status="", $message="")
    {
        http_response_code($code);
        if( !empty($status) && !empty($message) )
        {
            $response = array("status" => $status ,"message"=>$message);
            echo json_encode($response,JSON_PRETTY_PRINT);
        }
    }


    function getEmpleados()
    {
        if($_GET['action']=='Empleados')
        {
            $dbPDO = new DB();

            if(isset($_GET['Clave']))
            {
                $Clave=$_GET['Clave'];
                $response = $dbPDO->getEmpleado($Clave);
                echo json_encode($response,JSON_PRETTY_PRINT);
            }else{ //muestra todos los registros
                $response = $dbPDO->getAllEmpleados();
                echo json_encode($response,JSON_PRETTY_PRINT);
            }
        } else{
            $this->response(400);
        }
    }

    function saveEmpleados()
    {
        if($_GET['action']=='Empleados')
        {

            $obj = json_decode( file_get_contents('php://input') );
            $objArr = (array)$obj;
            if (empty($objArr))
            {
                $this->response(422,"error","Nothing to add. Check json");
            }
            else if(isset($obj->Apellidos))
            {
                $dbPDO = new DB();
                $dbPDO->insert($obj->Clave,$obj->Apellidos,$obj->Nombres,$obj->Puesto,$obj->Salario);
                $this->response(200,"","Empleado Guardado");
            }
            else{
                $this->response(422,"error","The property is not defined");
            }
        } else{
            $this->response(400);
        }
    }

    function updateEmpleados()
    {
        if( isset($_GET['action']) && isset($_GET['Clave']) )
        {
            if($_GET['action']=='Empleados')
            {
                $obj = json_decode( file_get_contents('php://input') );
                $objArr = (array)$obj;
                if (empty($objArr))
                {
                    $this->response(422,"error","Nothing to add. Check json");
                }else if(isset($obj->Apellidos))
                {

                    $dbPDO = new DB();
                    $dbPDO->update($_GET['Clave'], $obj->Apellidos,$obj->Nombres,$obj->Puesto,$obj->Salario);
                    $this->response(200,"","Empleado Actualizado");

                }else
                {
                    $this->response(422,"error","The property is not defined");
                }
                exit;
            }
        }
        $this->response(400);
    }


    function deleteEmpleado()
    {
        if( isset($_GET['action']) && isset($_GET['Clave']) )
        {
            if($_GET['action']=='Empleados')
            {
                $dbPDO = new DB();
                $dbPDO->delete($_GET['Clave']);
                $this->response(200,"","Empleado Eliminado");
            }
        }
        $this->response(400);
    }



}
?>