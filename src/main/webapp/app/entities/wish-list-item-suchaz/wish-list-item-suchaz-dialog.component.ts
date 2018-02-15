import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WishListItemSuchaz } from './wish-list-item-suchaz.model';
import { WishListItemSuchazPopupService } from './wish-list-item-suchaz-popup.service';
import { WishListItemSuchazService } from './wish-list-item-suchaz.service';
import { WishListSuchaz, WishListSuchazService } from '../wish-list-suchaz';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-wish-list-item-suchaz-dialog',
    templateUrl: './wish-list-item-suchaz-dialog.component.html'
})
export class WishListItemSuchazDialogComponent implements OnInit {

    wishListItem: WishListItemSuchaz;
    isSaving: boolean;

    wishlists: WishListSuchaz[];

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private wishListItemService: WishListItemSuchazService,
        private wishListService: WishListSuchazService,
        private itemService: ItemSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.wishListService.query()
            .subscribe((res: HttpResponse<WishListSuchaz[]>) => { this.wishlists = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.wishListItem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.wishListItemService.update(this.wishListItem));
        } else {
            this.subscribeToSaveResponse(
                this.wishListItemService.create(this.wishListItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<WishListItemSuchaz>>) {
        result.subscribe((res: HttpResponse<WishListItemSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: WishListItemSuchaz) {
        this.eventManager.broadcast({ name: 'wishListItemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackWishListById(index: number, item: WishListSuchaz) {
        return item.id;
    }

    trackItemById(index: number, item: ItemSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-wish-list-item-suchaz-popup',
    template: ''
})
export class WishListItemSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wishListItemPopupService: WishListItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.wishListItemPopupService
                    .open(WishListItemSuchazDialogComponent as Component, params['id']);
            } else {
                this.wishListItemPopupService
                    .open(WishListItemSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
