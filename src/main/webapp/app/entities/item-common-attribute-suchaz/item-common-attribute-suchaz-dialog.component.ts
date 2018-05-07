import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemCommonAttributeSuchaz } from './item-common-attribute-suchaz.model';
import { ItemCommonAttributeSuchazPopupService } from './item-common-attribute-suchaz-popup.service';
import { ItemCommonAttributeSuchazService } from './item-common-attribute-suchaz.service';
import { ItemSuchaz, ItemSuchazService } from '../item-suchaz';
import { ItemAttributeTypeSuchaz, ItemAttributeTypeSuchazService } from '../item-attribute-type-suchaz';
import { CategorySuchaz, CategorySuchazService } from '../category-suchaz';

@Component({
    selector: 'jhi-item-common-attribute-suchaz-dialog',
    templateUrl: './item-common-attribute-suchaz-dialog.component.html'
})
export class ItemCommonAttributeSuchazDialogComponent implements OnInit {

    itemCommonAttribute: ItemCommonAttributeSuchaz;
    isSaving: boolean;

    items: ItemSuchaz[];

    itemattributetypes: ItemAttributeTypeSuchaz[];

    categories: CategorySuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private itemCommonAttributeService: ItemCommonAttributeSuchazService,
        private itemService: ItemSuchazService,
        private itemAttributeTypeService: ItemAttributeTypeSuchazService,
        private categoryService: CategorySuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.itemService.query()
            .subscribe((res: HttpResponse<ItemSuchaz[]>) => { this.items = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.itemAttributeTypeService.query()
            .subscribe((res: HttpResponse<ItemAttributeTypeSuchaz[]>) => { this.itemattributetypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.categoryService.query()
            .subscribe((res: HttpResponse<CategorySuchaz[]>) => { this.categories = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.itemCommonAttribute.id !== undefined) {
            this.subscribeToSaveResponse(
                this.itemCommonAttributeService.update(this.itemCommonAttribute));
        } else {
            this.subscribeToSaveResponse(
                this.itemCommonAttributeService.create(this.itemCommonAttribute));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ItemCommonAttributeSuchaz>>) {
        result.subscribe((res: HttpResponse<ItemCommonAttributeSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: ItemCommonAttributeSuchaz) {
        this.eventManager.broadcast({ name: 'itemCommonAttributeListModification', content: 'OK'});
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

    trackItemAttributeTypeById(index: number, item: ItemAttributeTypeSuchaz) {
        return item.id;
    }

    trackCategoryById(index: number, item: CategorySuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-item-common-attribute-suchaz-popup',
    template: ''
})
export class ItemCommonAttributeSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemCommonAttributePopupService: ItemCommonAttributeSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.itemCommonAttributePopupService
                    .open(ItemCommonAttributeSuchazDialogComponent as Component, params['id']);
            } else {
                this.itemCommonAttributePopupService
                    .open(ItemCommonAttributeSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
