<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Product;
class CommentController extends Controller
{
    public function getComentWithIdProduct(Request $request){
        $id=$request->input('id_product');
        $cmt = DB::table('comment')
        ->where('id_product',$id)
        ->get();
        if(count($cmt) == 0){
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'không có coment',
                'data'=> null
                ]);
        }else{
                return response()->json([
                    'status' => 'SUCCESS',
                     'mess' => 'SUCCESS',
                    'data'=> $cmt
                    ]);
            }
       }
}