import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { SuchAzMenuSuchaz } from './such-az-menu-suchaz.model';
import { SuchAzMenuSuchazPopupService } from './such-az-menu-suchaz-popup.service';
import { SuchAzMenuSuchazService } from './such-az-menu-suchaz.service';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';

@Component({
    selector: 'jhi-such-az-menu-suchaz-dialog',
    templateUrl: './such-az-menu-suchaz-dialog.component.html'
})
export class SuchAzMenuSuchazDialogComponent implements OnInit {

    suchAzMenu: SuchAzMenuSuchaz;
    isSaving: boolean;

    items: ItemSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private suchAzMenuService: SuchAzMenuSuchazService,
        private itemService: ItemSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.suchAzMenu, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.suchAzMenu.id !== undefined) {
            this.subscribeToSaveResponse(
                this.suchAzMenuService.update(this.suchAzMenu));
        } else {
            this.subscribeToSaveResponse(
                this.suchAzMenuService.create(this.suchAzMenu));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SuchAzMenuSuchaz>>) {
        result.subscribe((res: HttpResponse<SuchAzMenuSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SuchAzMenuSuchaz) {
        this.eventManager.broadcast({ name: 'suchAzMenuListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackItemById(index: number, item: ItemSuchaz) {
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
    selector: 'jhi-such-az-menu-suchaz-popup',
    template: ''
})
export class SuchAzMenuSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private suchAzMenuPopupService: SuchAzMenuSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.suchAzMenuPopupService
                    .open(SuchAzMenuSuchazDialogComponent as Component, params['id']);
            } else {
                this.suchAzMenuPopupService
                    .open(SuchAzMenuSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
