<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Address;

class AddressController extends Controller
{
    public function LoadAddress(Request $request)
    {
        $idclient = $request->input('idClient');
        $add = DB::table('address')->where('id_client', $idclient)->get();
        if (count($add) > 0) {
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'SUCCESS',
                'data' => $add
            ]);
        } else {
            return response()->json([
                'status' => 'FAIL',
                'mess' => 'không tim thấy địa chỉ  ',
                'data' => null
            ]);
        }
    }

    public function CreateAddress(Request $request)
    {
        $address = new Address();
        $address->id_client = $request->input('idClient');
        $address->phone_number = $request->input('phone');
        $address->address = $request->input('address');
        $address->save();

        return response()->json($address);
    }

    public function updateAddress(Request $request)
    {
        $address = Address::find($request->input('id_address'));
        if ($address != null) {
            $address->phone_number = $request->input('phone');
            $address->address = $request->input('address');
            $address->save();
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'update thành công rồi nè :v ',
                'data' => null
            ]);
        } else {
            return response()->json([
                'status' => 'FAIL',
                'mess' => 'không tìm thấy thằng nào :v ',
                'data' => null
            ]);
        }
    }
    public function deleteAddress(Request $request)
    {
        $address = Address::find($request->input('id_address'));
        $address->delete();
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'xóa địa chỉ thành công rồi nè :v ',
            'data' => null
        ]);
    }
}
