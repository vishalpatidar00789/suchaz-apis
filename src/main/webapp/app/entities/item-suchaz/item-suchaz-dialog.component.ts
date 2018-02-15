import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemSuchaz } from './item-suchaz.model';
import { ItemSuchazPopupService } from './item-suchaz-popup.service';
import { ItemSuchazService } from './item-suchaz.service';
import { OfferSuchaz, OfferSuchazService } from '../offer-suchaz';
import { CategorySuchaz, CategorySuchazService } from '../category-suchaz';
import { VendorSuchaz, VendorSuchazService } from '../vendor-suchaz';
import { StoreSuchaz, StoreSuchazService } from '../store-suchaz';

@Component({
    selector: 'jhi-item-suchaz-dialog',
    templateUrl: './item-suchaz-dialog.component.html'
})
export class ItemSuchazDialogComponent implements OnInit {

    item: ItemSuchaz;
    isSaving: boolean;

    offers: OfferSuchaz[];

    categories: CategorySuchaz[];

    vendors: VendorSuchaz[];

    stores: StoreSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private itemService: ItemSuchazService,
        private offerService: OfferSuchazService,
        private categoryService: CategorySuchazService,
        private vendorService: VendorSuchazService,
        private storeService: StoreSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.offerService.query()
            .subscribe((res: HttpResponse<OfferSuchaz[]>) => { this.offers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.categoryService.query()
            .subscribe((res: HttpResponse<CategorySuchaz[]>) => { this.categories = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.vendorService.query()
            .subscribe((res: HttpResponse<VendorSuchaz[]>) => { this.vendors = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.storeService.query()
            .subscribe((res: HttpResponse<StoreSuchaz[]>) => { this.stores = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.item.id !== undefined) {
            this.subscribeToSaveResponse(
                this.itemService.update(this.item));
        } else {
            this.subscribeToSaveResponse(
                this.itemService.create(this.item));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ItemSuchaz>>) {
        result.subscribe((res: HttpResponse<ItemSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ItemSuchaz) {
        this.eventManager.broadcast({ name: 'itemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackOfferById(index: number, item: OfferSuchaz) {
        return item.id;
    }

    trackCategoryById(index: number, item: CategorySuchaz) {
        return item.id;
    }

    trackVendorById(index: number, item: VendorSuchaz) {
        return item.id;
    }

    trackStoreById(index: number, item: StoreSuchaz) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-item-suchaz-popup',
    template: ''
})
export class ItemSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemPopupService: ItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.itemPopupService
                    .open(ItemSuchazDialogComponent as Component, params['id']);
            } else {
                this.itemPopupService
                    .open(ItemSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
