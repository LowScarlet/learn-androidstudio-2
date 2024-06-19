package id.my.lowscarlet.uts_native.activity.pengeluaran;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.my.lowscarlet.uts_native.R;
import id.my.lowscarlet.uts_native.Utils;
import id.my.lowscarlet.uts_native.response.GetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // Don't Touch This
    private Activity activity;
    private List<Model> items;
    private Context context;
    private Handler handler;

    public Adapter(List<Model> items, Activity context, Handler handler) {
        // Don't Touch This
        this.items = items;
        this.context = context;
        this.handler = handler;
        this.activity = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemCountainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCountainer = itemView.findViewById(R.id.itemContainer);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crud_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        // TODO Edit This Scarlet
        TextView tanggalKeluar = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(tanggalKeluar);

        TextView jumlah = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(jumlah);

        TextView tujuan = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(tujuan);

        TextView penggunaId = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(penggunaId);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model data = items.get(position);

        // TODO Edit This Scarlet
        TextView tanggalKeluarTextView = (TextView) holder.itemCountainer.getChildAt(0);
        TextView jumlahTextView = (TextView) holder.itemCountainer.getChildAt(1);
        TextView tujuanTextView = (TextView) holder.itemCountainer.getChildAt(2);
        TextView penggunaIdTextView = (TextView) holder.itemCountainer.getChildAt(3);

        tanggalKeluarTextView.setText("Tanggal Keluar: "+data.tanggalKeluar);
        jumlahTextView.setText("Jumlah: "+data.jumlah);
        tujuanTextView.setText("Tujuan: "+data.tujuan);
        penggunaIdTextView.setText("PenggunaId: "+data.penggunaId);

        holder.itemView.setOnClickListener(v -> {
            showUpdateModal(data);
        });
    }

    private void showUpdateModal(Model model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.crud_update, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        LinearLayout updateContainer = dialogView.findViewById(R.id.updateContainer);

        // TODO Edit This Scarlet
        EditText jumlah = Utils.createEditText(context, "Jumlah");
        jumlah.setText(model.jumlah);
        updateContainer.addView(jumlah);

        EditText tujuan = Utils.createEditText(context, "tujuan");
        tujuan.setText(model.tujuan);
        updateContainer.addView(tujuan);

        EditText penggunaId = Utils.createEditText(context, "penggunaId");
        penggunaId.setText(model.penggunaId);
        updateContainer.addView(penggunaId);

        // Button
        Button updateButton = dialogView.findViewById(R.id.updateButton);
        Button deleteButton = dialogView.findViewById(R.id.deleteButton);

        // Button Handler
        updateButton.setOnClickListener(v -> {
            // TODO Edit This Scarlet
            String updatedJumlah =  jumlah.getText().toString();
            String updatedTujuan = tujuan.getText().toString();
            String updatedPenggunaId = penggunaId.getText().toString();

            model.jumlah = updatedJumlah;
            model.tujuan = updatedTujuan;
            model.penggunaId = updatedPenggunaId;

            // Do Your Job Handler
            handler.updateData(model.id, model).enqueue(new Callback<GetResponse<Model>>() {
                @Override
                public void onResponse(Call<GetResponse<Model>> call, Response<GetResponse<Model>> response) {
                    if (response.isSuccessful()) {
                        Model updatedData = response.body().getData();
                        activity.updateData(updatedData, dialog);
                    } else {
                        Toast.makeText(context, "Failed Update", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetResponse<Model>> call, Throwable t) {
                    Toast.makeText(context, "Network error, please try again", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Button Handler
        deleteButton.setOnClickListener(v -> {
            // Do Your Job Handler
            handler.updateData(model.id, model).enqueue(new Callback<GetResponse<Model>>() {
                @Override
                public void onResponse(Call<GetResponse<Model>> call, Response<GetResponse<Model>> response) {
                    if (response.isSuccessful()) {
                        Model updatedData = response.body().getData();
                        activity.deleteData(updatedData, dialog);
                    } else {
                        Toast.makeText(context, "Failed Update", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GetResponse<Model>> call, Throwable t) {
                    Toast.makeText(context, "Network error, please try again", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Show the fcking button
        dialog.show();
    }

    @Override
    public int getItemCount() {
        // Don't Touch This
        return items.size();
    }

    public void addItem(Model model) {
        // Don't Touch This
        items.add(model);
        notifyItemInserted(items.size() - 1);
    }

    public void updateItem(Model updatedItem) {
        // Don't Touch This
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id.equals(updatedItem.id)) {
                items.set(i, updatedItem);
                notifyItemChanged(i);
                break;
            }
        }
    }

    public void deleteItem(Model updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id.equals(updatedItem.id)) {
                items.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
}
