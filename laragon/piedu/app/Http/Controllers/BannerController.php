<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Product;
class bannerController extends Controller
{
    public function getBanner(){
        $banner = DB::table('banners')
        ->orderBy('id_banner', 'desc')
        ->take(6)
        ->get();
        return response()->json($banner);
       }
}