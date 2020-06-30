<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Carbon\Carbon;
use App\DetailBill;
use App\Bill;

class BillController extends Controller
{

    public function getListBillDetail(Request $request)  // ok 
    { //lay san pham da dat hang
        $pro = DB::table('detail_bill')
            ->where('id_bill', $request->input('id_bill'))
            ->leftJoin('product', 'detail_bill.id_product', '=', 'product.id_product')
            ->select('product.name', 'product.image', 'product.describe', 'detail_bill.*')
            ->get();
            if(count($pro)>0){
                return response()->json([
                    'status' => 'SUCCESS',
                    'mess' => 'MESS',
                    'data' => $pro
                ]);
            }else{
                return response()->json([
                    'status' => 'FAIL',
                    'mess' => 'FAIL',
                    'data' => null
                ]);
            }

    }


    public function loadbill(Request $rq)
    {
        $bill = DB::table('bill')
            ->where('id_client', $rq->input('id_client'))
            ->where('status_bill', $rq->input('status_bill')) // b1
            ->get();

        if (count($bill) > 0) {
            return response()->json(['status' => 'SUCCESS', 'mess' => 'SUCCESS', 'data' => $bill]);
        } else {
            return response()->json(['status' => 'FAIL', 'mess' => 'Chưa có bill ', 'data' => null]);
        }
    }

    // public function loadDetailbill(Request $rq)
    // {
    //     $billDetail = DB::table('detail_bill')
    //         ->where('id_bill', $rq->input('id_bill'))
    //         ->get();
    //     if (count($billDetail) > 0) {
    //         return response()->json(['status' => 'SUCCESS', 'mess' => 'SUCCESS', 'data' => $billDetail]);
    //     } else {
    //         return response()->json(['status' => 'FAIL', 'mess' => 'Chưa có detailbill ', 'data' => null]);
    //     }
    // }

    public function addExCart(Request $rq)
    {
        // check id product trước
        $idproduct = $rq->input('id_product');
        $idbill = $rq->input('id_bill');
        $price = $rq->input('price');

        $check = DB::table('detail_bill')
            ->where('id_bill', $idbill)
            ->where('id_product', $idproduct)
            ->first();
        //dd($check);
        if ($check == null) {
            // thêm mới vào bill detail 
            $bill = new DetailBill();
            $bill->id_bill = $idbill;
            $bill->id_product = $idproduct;
            $bill->price = $price;
            $bill->quantity = 1;
            $bill->save();
        } else {
            DB::table('detail_bill')
                ->where('id_bill', $idbill)
                ->where('id_product', $idproduct)
                ->increment('quantity');
        }

        return response()->json(['status' => 'SUCCESS', 'mess' => 'đã thêm sản phẩm ', 'data' => null]);
    }

    public function createBill(Request $rq)
    {
        $mytime = Carbon::now(); // lấy ngày hiện tại 
        $bill = new Bill();
        $detailBill = new DetailBill();

        $bill->time = $mytime;
        $bill->id_address = $rq->input('id_address');
        $bill->id_client = $rq->input('id_client');
        $bill->status_bill = 'b1';
        $bill->save();

        $detailBill->id_bill = $bill->id;
        $detailBill->id_product = $rq->input('id_product');
        $detailBill->price = $rq->input('price');
        $detailBill->quantity = 1;
        $detailBill->save();
        return response()->json(['status' => 'SUCCESS', 'mess' => 'đã tạo hóa đơn  ', 'data' => $bill]);
    }

    public function changeCart(Request $rq)
    {
        $type = $rq->input('type');
        if ($type == 'ADD') {
            $billDetail = DB::table('detail_bill')
                ->where('id_detail', $rq->input('id_detail'))
                ->where('id_product', $rq->input('id_product'))
                ->increment('quantity');
        } else {
            $billDetail = DB::table('detail_bill')
                ->where('id_detail', $rq->input('id_detail'))
                ->where('id_product', $rq->input('id_product'))
                ->decrement('quantity');
        }

        return response()->json(['status' => 'SUCCESS', 'mess' => 'đã thay đổi nè', 'data' => null]);
    }

    public function orderCart(Request $rq)
    {
        $id_bill=null;
        $data =  json_decode($rq->getContent(), true); // ok ok ne
        foreach ($data as $value) {
            $id_bill=$value['id_bill'];
            DB::table('product')
            ->where('id_product', $value['id_product'])
            ->decrement('quantity',$value['quantity']);
        }

        $affected = DB::table('bill')
        ->where('id_bill', $id_bill)
        ->update(['status_bill' => 'b2']);

        return response()->json(['status' => 'SUCCESS', 'mess' => 'đặt hàng thành công rồi nè', 'data' => null]);
    }

    public function removeCart(Request $rq)
    {
        $billDetail = DB::table('detail_bill')
        ->where('id_detail', $rq->input('id_detail'))
        ->delete();

        return response()->json(['status' => 'SUCCESS', 'mess' => 'đã xóa thành công', 'data' => null]);
    }



}
