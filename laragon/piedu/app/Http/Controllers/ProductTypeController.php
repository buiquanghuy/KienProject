<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Product;
class ProductTypeController extends Controller
{
    public function getProductType(){
        $product_type = DB::table('type_product')
        ->get();
        return response()->json($product_type);
       }
}