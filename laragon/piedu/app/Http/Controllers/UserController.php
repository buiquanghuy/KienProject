<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Client;

class UserController extends Controller
{
    public function Login(Request $request)
    {
        $tk = $request->input('account_name');
        $mk = $request->input('password');

        $user = DB::table('client')
            ->where('account_name', $tk)
            ->where('password', $mk)->first();
        if ($user == null) {
            return response()->json([
                'status' => 'FAIL',
                'mess' => 'tài khoản hoặc mật khẩu không đúng',
                'data' => null
            ]);
        } else {
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'SUCCESS',
                'data' => $user
            ]);
        }
    }
    public function Register(Request $request)
    {
        $name = $request->input('name');
        $phone = $request->input('phone');
        $email = $request->input('email');
        $account_name = $request->input('account_name');
        $password = $request->input('password');

        $acc = DB::table('client')->where('account_name', $account_name)->first();
        $mail = DB::table('client')->where('email', $email)->first();

        if ($acc != null) {
            return response()->json([
                'status' => 'FAIL',
                'mess' => 'tên tài khoản đã có người sử dụng',
                'data' => null
            ]);
        }

        if ($mail != null) {
            return response()->json([
                'status' => 'FAIL',
                'mess' => 'email này đã có người sử dụng !',
                'data' => null
            ]);
        } else {
            $data = array(
                'name' => $name,
                'phone' => $phone,
                'email' => $email,
                'account_name' => $account_name,
                'password' => $password,
            );
            $lastId = DB::table('client')->insertGetId($data);
            return response()->json([
                'status' => 'SUCCESS',
                'mess' => 'SUCCESS',
                'data' => $lastId
            ]);
        }
    }

    public function updateUser(Request $request)
    {
        $client = Client::find($request->input('id_client'));

        $client->name = $request->input('name');
        $client->phone = $request->input('phone');
        $client->email = $request->input('email');
        $client->save();
        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'cập nhật thành công ',
            'data' => null
        ]);
    }

    public function getget()
    {
        $client = DB::table('address')->get();

        return response()->json([
            'status' => 'SUCCESS',
            'mess' => 'cập nhật thành công ',
            'data' => $client
        ]);
    }

}
