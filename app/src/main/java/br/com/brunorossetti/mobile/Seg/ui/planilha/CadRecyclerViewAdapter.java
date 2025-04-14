package br.com.brunorossetti.mobile.Seg.ui.planilha;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.brunorossetti.mobile.Seg.databinding.FragmentCadUsuarioBinding;
import br.com.brunorossetti.mobile.Seg.model.Usuario;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Usuario}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CadRecyclerViewAdapter extends RecyclerView.Adapter<CadRecyclerViewAdapter.ViewHolder> {

    private final List<Usuario> mValues;

    public CadRecyclerViewAdapter(List<Usuario> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCadUsuarioBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getUsuario());
        holder.mContentView.setText(mValues.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Usuario mItem;

        public ViewHolder(FragmentCadUsuarioBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}