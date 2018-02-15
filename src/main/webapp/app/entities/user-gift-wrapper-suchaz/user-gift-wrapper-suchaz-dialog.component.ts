import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UserGiftWrapperSuchaz } from './user-gift-wrapper-suchaz.model';
import { UserGiftWrapperSuchazPopupService } from './user-gift-wrapper-suchaz-popup.service';
import { UserGiftWrapperSuchazService } from './user-gift-wrapper-suchaz.service';
import { SuchAzUserSuchaz, SuchAzUserSuchazService } from '../such-az-user-suchaz';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';
import { GiftWrapperSuchaz, GiftWrapperSuchazService } from '../gift-wrapper-suchaz';

@Component({
    selector: 'jhi-user-gift-wrapper-suchaz-dialog',
    templateUrl: './user-gift-wrapper-suchaz-dialog.component.html'
})
export class UserGiftWrapperSuchazDialogComponent implements OnInit {

    userGiftWrapper: UserGiftWrapperSuchaz;
    isSaving: boolean;

    suchazusers: SuchAzUserSuchaz[];

    items: ItemSuchaz[];

    giftwrappers: GiftWrapperSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private userGiftWrapperService: UserGiftWrapperSuchazService,
        private suchAzUserService: SuchAzUserSuchazService,
        private itemService: ItemSuchazService,
        private giftWrapperService: GiftWrapperSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.suchAzUserService.query()
            .subscribe((res: HttpResponse<SuchAzUserSuchaz[]>) => { this.suchazusers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.giftWrapperService.query()
            .subscribe((res: HttpResponse<GiftWrapperSuchaz[]>) => { this.giftwrappers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.userGiftWrapper, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userGiftWrapper.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userGiftWrapperService.update(this.userGiftWrapper));
        } else {
            this.subscribeToSaveResponse(
                this.userGiftWrapperService.create(this.userGiftWrapper));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserGiftWrapperSuchaz>>) {
        result.subscribe((res: HttpResponse<UserGiftWrapperSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserGiftWrapperSuchaz) {
        this.eventManager.broadcast({ name: 'userGiftWrapperListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSuchAzUserById(index: number, item: SuchAzUserSuchaz) {
        return item.id;
    }

    trackItemById(index: number, item: ItemSuchaz) {
        return item.id;
    }

    trackGiftWrapperById(index: number, item: GiftWrapperSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-user-gift-wrapper-suchaz-popup',
    template: ''
})
export class UserGiftWrapperSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userGiftWrapperPopupService: UserGiftWrapperSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userGiftWrapperPopupService
                    .open(UserGiftWrapperSuchazDialogComponent as Component, params['id']);
            } else {
                this.userGiftWrapperPopupService
                    .open(UserGiftWrapperSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
