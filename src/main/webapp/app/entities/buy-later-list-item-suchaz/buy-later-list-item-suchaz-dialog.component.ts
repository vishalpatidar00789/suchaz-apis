import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BuyLaterListItemSuchaz } from './buy-later-list-item-suchaz.model';
import { BuyLaterListItemSuchazPopupService } from './buy-later-list-item-suchaz-popup.service';
import { BuyLaterListItemSuchazService } from './buy-later-list-item-suchaz.service';
import { BuyLaterListSuchaz, BuyLaterListSuchazService } from '../buy-later-list-suchaz';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-buy-later-list-item-suchaz-dialog',
    templateUrl: './buy-later-list-item-suchaz-dialog.component.html'
})
export class BuyLaterListItemSuchazDialogComponent implements OnInit {

    buyLaterListItem: BuyLaterListItemSuchaz;
    isSaving: boolean;

    buylaterlists: BuyLaterListSuchaz[];

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private buyLaterListItemService: BuyLaterListItemSuchazService,
        private buyLaterListService: BuyLaterListSuchazService,
        private itemService: ItemSuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.buyLaterListService.query()
            .subscribe((res: HttpResponse<BuyLaterListSuchaz[]>) => { this.buylaterlists = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.buyLaterListItem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.buyLaterListItemService.update(this.buyLaterListItem));
        } else {
            this.subscribeToSaveResponse(
                this.buyLaterListItemService.create(this.buyLaterListItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BuyLaterListItemSuchaz>>) {
        result.subscribe((res: HttpResponse<BuyLaterListItemSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BuyLaterListItemSuchaz) {
        this.eventManager.broadcast({ name: 'buyLaterListItemListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackBuyLaterListById(index: number, item: BuyLaterListSuchaz) {
        return item.id;
    }

    trackItemById(index: number, item: ItemSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-buy-later-list-item-suchaz-popup',
    template: ''
})
export class BuyLaterListItemSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private buyLaterListItemPopupService: BuyLaterListItemSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.buyLaterListItemPopupService
                    .open(BuyLaterListItemSuchazDialogComponent as Component, params['id']);
            } else {
                this.buyLaterListItemPopupService
                    .open(BuyLaterListItemSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
