package id.my.ardy.uts_native.activity.book;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import id.my.ardy.uts_native.R;
import id.my.ardy.uts_native.Utils;
import id.my.ardy.uts_native.response.GetResponse;
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

        // TODO Edit This Scarlet
        int AdapterIcon = R.drawable.baseline_account_circle_24;
        ImageView itemIcon = view.findViewById(R.id.itemIcon);
        itemIcon.setImageResource(AdapterIcon);

         ViewHolder viewHolder = new ViewHolder(view);

        // TODO Edit This Scarlet
        TextView title = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(title);
        TextView isbn = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(isbn);
        TextView publicationYear = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(publicationYear);
        TextView genre = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(genre);
        TextView authorId = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(authorId);
        TextView publisherId = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(publisherId);
        TextView categoryId = Utils.createTextView(context);
        viewHolder.itemCountainer.addView(categoryId);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model data = items.get(position);

        // TODO Edit This Scarlet
        ((TextView) holder.itemCountainer.getChildAt(0)).setText(String.format("title: %s", data.title));
        ((TextView) holder.itemCountainer.getChildAt(1)).setText(String.format("isbn: %s", data.isbn));
        ((TextView) holder.itemCountainer.getChildAt(2)).setText(String.format("publicationYear: %s", data.publicationYear));
        ((TextView) holder.itemCountainer.getChildAt(3)).setText(String.format("genre: %s", data.genre));
        ((TextView) holder.itemCountainer.getChildAt(4)).setText(String.format("authorId: %s", data.authorId));
        ((TextView) holder.itemCountainer.getChildAt(5)).setText(String.format("publisherId: %s", data.publisherId));
        ((TextView) holder.itemCountainer.getChildAt(6)).setText(String.format("categoryId: %s", data.categoryId));

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
        TextView itemId = dialogView.findViewById(R.id.itemId);
        itemId.setText(model.id);

        // TODO Edit This Scarlet
        EditText title = Utils.createEditText(context, "title");
        title.setText(model.title);
        updateContainer.addView(title);
        EditText isbn = Utils.createEditText(context, "isbn");
        isbn.setText(model.isbn);
        updateContainer.addView(isbn);
        EditText publicationYear = Utils.createEditText(context, "publicationYear");
        publicationYear.setText(model.publicationYear);
        updateContainer.addView(publicationYear);
        EditText genre = Utils.createEditText(context, "genre");
        genre.setText(model.genre);
        updateContainer.addView(genre);
        EditText authorId = Utils.createEditText(context, "authorId");
        authorId.setText(model.authorId);
        updateContainer.addView(authorId);
        EditText publisherId = Utils.createEditText(context, "publisherId");
        publisherId.setText(model.publisherId);
        updateContainer.addView(publisherId);
        EditText categoryId = Utils.createEditText(context, "categoryId");
        categoryId.setText(model.categoryId);
        updateContainer.addView(categoryId);

        // Button
        Button updateButton = dialogView.findViewById(R.id.updateButton);
        Button deleteButton = dialogView.findViewById(R.id.deleteButton);
        Button copyIdButton = dialogView.findViewById(R.id.copyIdButton);

        copyIdButton.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", model.id);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "ID copied to clipboard", Toast.LENGTH_SHORT).show();
        });

        // Button Handler
        updateButton.setOnClickListener(v -> {
            Map<String, Object> updatedModel = new HashMap<>();
            // TODO Edit This Scarlet

            if (!Objects.equals(model.title, title.getText().toString())) {
                updatedModel.put("title", title.getText().toString());
            }

            if (!Objects.equals(model.isbn, isbn.getText().toString())) {
                updatedModel.put("isbn", isbn.getText().toString());
            }

            if (!Objects.equals(model.publicationYear, publicationYear.getText().toString())) {
                updatedModel.put("publicationYear", publicationYear.getText().toString());
            }

            if (!Objects.equals(model.genre, genre.getText().toString())) {
                updatedModel.put("genre", genre.getText().toString());
            }

            if (!Objects.equals(model.authorId, authorId.getText().toString())) {
                updatedModel.put("authorId", authorId.getText().toString());
            }

            if (!Objects.equals(model.publisherId, publisherId.getText().toString())) {
                updatedModel.put("publisherId", publisherId.getText().toString());
            }

            if (!Objects.equals(model.categoryId, categoryId.getText().toString())) {
                updatedModel.put("categoryId", categoryId.getText().toString());
            }

            // Do Your Job Handler
            handler.updateData(model.id, updatedModel).enqueue(new Callback<GetResponse<Model>>() {
                @Override
                public void onResponse(Call<GetResponse<Model>> call, Response<GetResponse<Model>> response) {
                    if (response.isSuccessful()) {
                        Model updatedData = response.body().getData();
                        activity.updateData(updatedData, dialog);
                    } else {
                        if (response.code() == 400) {
                            Gson gson = new Gson();
                            String responseBodyJson = null;
                            try {
                                responseBodyJson = response.errorBody().string();
                                TextView errorLog = dialog.findViewById(R.id.updateErrorLog);
                                errorLog.setText(responseBodyJson);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(context, "Failed to create Data", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to create Data. Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
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
            handler.deleteData(model.id).enqueue(new Callback<GetResponse<Model>>() {
                @Override
                public void onResponse(Call<GetResponse<Model>> call, Response<GetResponse<Model>> response) {
                    if (response.isSuccessful()) {
                        Model updatedData = response.body().getData();
                        activity.deleteData(updatedData, dialog);
                    } else {
                        if (response.code() == 400) {
                            Gson gson = new Gson();
                            String responseBodyJson = null;
                            try {
                                responseBodyJson = response.errorBody().string();
                                TextView errorLog = dialog.findViewById(R.id.updateErrorLog);
                                errorLog.setText(responseBodyJson);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(context, "Failed to delete Data", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to delete Data. Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
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
