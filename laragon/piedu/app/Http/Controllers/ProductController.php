<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Product;
class ProductController extends Controller
{
    public function getfavorite(){ // lấy sản phẩm yêu thích
        $pro = DB::table('product')
        ->where('love',1)->get();
        if($pro == null){
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'hiện tại éo có sản phẩm yêu thích',
                'data'=> null
                ]);
        }else{
                return response()->json([
                    'status' => 'SUCCESS',
                     'mess' => 'SUCCESS',
                    'data'=> $pro
                    ]);
            }
       }

       public function getAll(){ // lấy tất cả sản phẩm

        $pro = DB::table('product')->get();
        if($pro == null){
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'không có sản phẩm nào ',
                'data'=> null
                ]);
        }else{
                return response()->json([
                    'status' => 'SUCCESS',
                     'mess' => 'SUCCESS',
                    'data'=> $pro
                    ]);
            }
       }

       public function getAllWithType(Request $request){ // lấy sản phẩm theo mã loại sản phẩm 
        $id=$request->input('id_type');
        $pro = DB::table('product')
        ->where('id_type',$id)->get();

        if(count($pro) > 0){
            return response()->json([
                'status' => 'SUCCESS',
                 'mess' => 'SUCCESS',
                'data'=> $pro
                ]);
           
        }else{
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'hiện tại không sản phẩm nào thuộc loại này',
                'data'=> null
                ]);    
            }
       }

       public function getAllWithID(Request $request){ // lấy sản phẩm theo mã loại sản phẩm 
        $id=$request->input('id_product');
        $pro = DB::table('product')
        ->where('id_product',$id)->get();
        if(count($pro) > 0){
            return response()->json([
                'status' => 'SUCCESS',
                 'mess' => 'SUCCESS',
                'data'=> $pro
                ]);
           
        }else{
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'hiện tại không sản phẩm nào',
                'data'=> null
                ]);    
            }
       }

       public function getNewProduct(){ // lấy 15 sản phẩm mới nhất
        $pro = DB::table('product') 
        ->orderBy('id_product', 'desc')
        ->take(15)
        ->get();
        if(count($pro) == 0){
            return response()->json([
                'status' => 'FAIL',
                 'mess' => 'không có sản phẩm nào ',
                'data'=> null
                ]);
        }else{
                return response()->json([
                    'status' => 'SUCCESS',
                     'mess' => 'SUCCESS',
                    'data'=> $pro
                    ]);
            }
       }

       public function searchProduct(Request $request){ // load sản phẩm theo tên (gần đúng)
        $name=$request->input('name');
        $pro = DB::table('product')
                ->where('name', 'like', "%{$name}%")
                ->orWhere('describe', 'like',"%{$name}%")
                ->orWhere('size', 'like',"%{$name}%")
                ->orWhere('trade', 'like',"%{$name}%")
                ->orWhere('producer', 'like',"%{$name}%")
                ->get();
                if(count($pro) == 0){
                    return response()->json([
                        'status' => 'FAIL',
                         'mess' => 'không có sản phẩm nào ',
                        'data'=> null
                        ]);
                }else{
                        return response()->json([
                            'status' => 'SUCCESS',
                             'mess' => 'SUCCESS',
                            'data'=> $pro
                            ]);
                    }
    }

    public function getTopProduct(){ // load sản phẩm theo tên (gần đúng)
        $pro = DB::table('detail_bill')
        ->leftJoin('product', 'detail_bill.id_product', '=', 'product.id_product')
        ->select('product.*')
        ->take(5)
        ->get();
        return response()->json([
            'status' => 'SUCCESS',
             'mess' => 'SUCCESS',
            'data'=> $pro
            ]);
    }

    public function getProductForU(){ // load sản phẩm theo tên (gần đúng)
        $pro = DB::table('product')
        ->inRandomOrder()
        ->limit(5)
        ->get();
       
        return response()->json([
            'status' => 'SUCCESS',
             'mess' => 'SUCCESS',
            'data'=> $pro
            ]);
    }






    // public function updateproduct(){
    //     $affected = DB::table('product')
    //           ->update(['image' => 'https://salt.tikicdn.com/cache/w780/ts/product/b8/92/03/cce22f2f757a863b76d0fac92a3cefd5.jpg',
    //                     'name'=>'Sách nè',
    //                     'describe'=>'một thứ gì đó',
    //                     'price'=>10000000,
    //                     ]);
    // }


}