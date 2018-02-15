/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemAttributeTypeSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz-delete-dialog.component';
import { ItemAttributeTypeSuchazService } from '../../../../../../main/webapp/app/entities/item-attribute-type-suchaz/item-attribute-type-suchaz.service';

describe('Component Tests', () => {

    describe('ItemAttributeTypeSuchaz Management Delete Component', () => {
        let comp: ItemAttributeTypeSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<ItemAttributeTypeSuchazDeleteDialogComponent>;
        let service: ItemAttributeTypeSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemAttributeTypeSuchazDeleteDialogComponent],
                providers: [
                    ItemAttributeTypeSuchazService
                ]
            })
            .overrideTemplate(ItemAttributeTypeSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemAttributeTypeSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemAttributeTypeSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
