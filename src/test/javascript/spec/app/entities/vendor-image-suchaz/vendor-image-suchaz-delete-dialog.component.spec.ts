/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { VendorImageSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz-delete-dialog.component';
import { VendorImageSuchazService } from '../../../../../../main/webapp/app/entities/vendor-image-suchaz/vendor-image-suchaz.service';

describe('Component Tests', () => {

    describe('VendorImageSuchaz Management Delete Component', () => {
        let comp: VendorImageSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<VendorImageSuchazDeleteDialogComponent>;
        let service: VendorImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [VendorImageSuchazDeleteDialogComponent],
                providers: [
                    VendorImageSuchazService
                ]
            })
            .overrideTemplate(VendorImageSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VendorImageSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VendorImageSuchazService);
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
